package atua.anddev.globaltv.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "CHANNEL")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    private Channel parent;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "category")
    private String category;

    @Column(name = "plistname")
    private String plistname;

    public Channel(String name, String category, String url, String plistname)
    {
        this.name = name;
        this.category = category;
        this.url = url;
        this.plistname = plistname;
    }

    public String getName()
    {
        return this.name;
    }

    public String getUrl()
    {
        return this.url;
    }

    public String getCategory()
    {
        return this.category;
    }

    public String getPlistname()
    {
        return this.plistname;
    }
}
