package atua.anddev.globaltv.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "PLAYLIST")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "type")
    private Integer type;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "updateDate")
    private Date updateDate;

    public Playlist(String name, String url, Integer type, Boolean enable) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.enable = enable;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public Boolean isEnable() {
        return this.enable;
    }

    public Integer getType() {
        return this.type;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

}
