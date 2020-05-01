package hu.unideb.inf.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Size {
    @Id
    @GeneratedValue
    private long sizeId;
    private String sizeName;

    @ManyToMany(mappedBy = "sizes")
    private Set<Product> products = new HashSet<Product>();

    public Size() {
    }

    public long getSizeId() {
        return sizeId;
    }

    public void setSizeId(long sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
}
