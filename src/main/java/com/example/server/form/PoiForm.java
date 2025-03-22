package com.example.server.form;
import com.example.server.pojo.Pic;
import lombok.Data;
import java.util.List;

@Data
public class PoiForm {

        private Integer id;
        public String name;
        public String description;
        public float lat;
        public float lng;
        public String coverUrl;
        public List<Pic> pics;
}
