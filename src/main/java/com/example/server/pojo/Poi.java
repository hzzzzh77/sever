package com.example.server.pojo;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("poi_table")
public class Poi {
    private Integer id;
    public String name;
    public String description;
    public float lat;
    public float lng;
    public String coverUrl;

}
