package model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {
    private String name;
    private String description;
//    private List<String> images;
    private List<Integer> images;
    private String category;
}
