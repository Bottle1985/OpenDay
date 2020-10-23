package uni.tbd.openday.Module.main.chats.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import uni.tbd.openday.Notifications.MyReponse;
import uni.tbd.openday.Notifications.Sender;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA2t-PsiI:APA91bFGfZmRFk7IjYGLrz9L8hEzTLxBBXAUBSE2X2S-nbtTOkXYm5ucu0YCUntPJzRraBj-KA7IK_N7RJfD5k3AkPsbkjhJmy371S_6jcCzusVeSIT867Hj0vV-wPL90rKkGr5K6r6u"
            }
    )
    @POST("fcm/send")
    Call<MyReponse> sendNotification(@Body Sender body);
}
