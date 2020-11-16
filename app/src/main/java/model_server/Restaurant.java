package model_server;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
}
