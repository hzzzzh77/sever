package com.example.server.vo;
import lombok.Data;

import java.util.List;

@Data
public class PoiVo {
    public String name;
    public String description;
    private Integer id;
    private List pics;

}
