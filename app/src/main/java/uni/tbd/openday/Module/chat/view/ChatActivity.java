package uni.tbd.openday.Module.chat.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uni.tbd.openday.Module.main.chats.model.APIService;
import uni.tbd.openday.Module.main.users.model.User;
import uni.tbd.openday.Notifications.Client;
import uni.tbd.openday.Notifications.Data;
import uni.tbd.openday.Notifications.MyReponse;
import uni.tbd.openday.Notifications.Sender;
import uni.tbd.openday.Notifications.Token;
import uni.tbd.openday.R;
import uni.tbd.openday.databinding.ActivityChatBinding;
import uni.tbd.openday.Module.chat.model.Message;
import uni.tbd.openday.Utils.Const;

import static okhttp3.RequestBody.create;

public class ChatActivity extends AppCompatActivity {

    private static final String EXTRA_CHAT_ID = "uni.tbd.openday.module.chat.view.ChatActivity.chat_id";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String TAG = "ChatActivity";

    private ActivityChatBinding bind;
    private MessageAdapter adapter;

    private FirebaseDatabase db;
    private FirebaseAuth auth;
    DatabaseReference reference;
    private OkHttpClient client;
    FirebaseUser firebaseUser;
    private String chatId;
    private String uid;
    boolean notify = false;
    APIService apiService;
    public static Intent getIntent(String chatId, Context context) {
        Intent i = new Intent(context, ChatActivity.class);
        i.putExtra(EXTRA_CHAT_ID, chatId);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bind = DataBindingUtil.setContentView(this, R.layout.activity_chat);

        chatId = getIntent().getStringExtra(EXTRA_CHAT_ID);
        if (chatId == null) finish();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        client = new OkHttpClient();

        bind.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notify =true;
                if (bind.content.getText().toString().isEmpty()) return;
                createMessage(bind.content.getText().toString());
                bind.content.setText("");
            }
        });
        apiService = Client.getClient("http://fcm.googleapis.com/").create(APIService.class);
        adapter = new MessageAdapter(this, bind.messageList, auth.getCurrentUser().getUid());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        bind.messageList.setLayoutManager(linearLayoutManager);
        bind.messageList.setAdapter(adapter);

        db.getReference().child("chat").child(chatId).child("message_id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                db.getReference().child("message").child((String)dataSnapshot.getValue()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        adapter.addMessage(dataSnapshot.getValue(Message.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void createMessage(String content) {
        String key = db.getReference().child("message").push().getKey();
        db.getReference().child("chat").child(chatId).child("message_id").push().setValue(key);
        Message message = new Message(auth.getCurrentUser().getUid(), auth.getCurrentUser().getDisplayName(), content, auth.getCurrentUser().getPhotoUrl().toString());
        db.getReference().child("message").child(key).setValue(message);

        try {

            JSONObject obj = new JSONObject();
            obj.put("to", "/topics/" + chatId);
            JSONObject data = new JSONObject();
            data.put("chat_id", chatId);
            data.put("owner_nickname", auth.getCurrentUser().getDisplayName());
            data.put("owner_photo_url", auth.getCurrentUser().getPhotoUrl().toString());
            data.put("content", content);
            data.put("date", message.getDate());
            data.put("owner_id", auth.getCurrentUser().getUid());
            obj.put("data", data);

            client.newCall(new Request.Builder()
                    .url(Const.NOTIFICATION_URL)
                    .addHeader("Content-Type", Const.CONTENT_TYPE)
                    .addHeader("Authorization", Const.AUTH_KEY)
                    .post(create(JSON, obj.toString()))
                    .build()
            ).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "Error Request " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d(TAG, "Responce " + response.toString());
                }
            });
            final String msg = bind.content.getText().toString();
            reference = FirebaseDatabase.getInstance().getReference("user").child(firebaseUser.getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                    if (notify) {
                        sendNotification(chatId, user.getName(), msg);
                    }
                    notify = false;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void sendNotification(String chatId,String username, String message){
        DatabaseReference token = FirebaseDatabase.getInstance().getReference("Tokens");
        Query query = token.orderByKey().equalTo(chatId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    Token token1 = snapshot1.getValue(Token.class);
                    Data data = new Data(firebaseUser.getUid(),R.mipmap.ic_launcher,username+": "+message, "New Message",auth.getCurrentUser().getUid());
                    Sender sender = new Sender(data,token1.getToken());

                    apiService.sendNotification(sender)
                            .enqueue(new retrofit2.Callback<MyReponse>() {
                                @Override
                                public void onResponse(retrofit2.Call<MyReponse> call, retrofit2.Response<MyReponse> response) {
                                    if (response.code()==200){
                                        if (response.body().success==1){
                                            Toast.makeText(ChatActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(retrofit2.Call<MyReponse> call, Throwable t) {

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
