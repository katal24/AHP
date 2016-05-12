package model;

import javax.persistence.*;

/**
 * Created by dawid on 12.05.16.
 */
@Entity
@Table(name = "surveys", schema = "", catalog = "talaga2")
public class SurveysEntity {
    private int id;
    private String type;
    private String owner;
    private String name;
    private String categories;
    private String variants;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "categories")
    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Basic
    @Column(name = "variants")
    public String getVariants() {
        return variants;
    }

    public void setVariants(String variants) {
        this.variants = variants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveysEntity that = (SurveysEntity) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (categories != null ? !categories.equals(that.categories) : that.categories != null) return false;
        if (variants != null ? !variants.equals(that.variants) : that.variants != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (variants != null ? variants.hashCode() : 0);
        return result;
    }

    public SurveysEntity(String type, String owner, String name, String categories, String variants) {
        this.type = type;
        this.owner = owner;
        this.name = name;
        this.categories = categories;
        this.variants = variants;
    }

    public SurveysEntity() {
    }

    @Override
    public String toString() {
        return "SurveysEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                ", categories='" + categories + '\'' +
                ", variants='" + variants + '\'' +
                '}';
    }
}
