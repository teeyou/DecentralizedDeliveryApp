package model_server;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderState {
    @SerializedName("state") //restApi에 작성한 이름으로 바꾸면 됩니다.
    private String state;

    @SerializedName("time") //restApi에 작성한 이름으로 바꾸면 됩니다.
    private String time;

    @SerializedName("name")  //restApi에 작성한 이름으로 바꾸면 됩니다.
    private String restaurantName;

    @SerializedName("menu")  //restApi에 작성한 이름으로 바꾸면 됩니다.
    private String menu;
//    private Menu menu;
}
