package model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant implements Serializable {
    private String name;
    private String description;
//    private List<String> images;
    private List<Integer> images;
    private String category;
//    private List<String> filterList;
//    private String logoImage;
    private String location;
}
