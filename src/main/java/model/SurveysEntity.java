package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dawid on 04.06.16.
 */
@Entity
@Table(name = "surveys", schema = "", catalog = "talaga2")
public class SurveysEntity {
    private long id;
    private String type;
    private String owner;
    private String name;
    private String categories;
    private String variants;
    private String completed;
    private String check1;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Basic
    @Column(name = "completed")
    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Basic
    @Column(name = "check1")
    public String getCheck() {
        return check1;
    }

    public void setCheck(String check1) {
        this.check1 = check1;
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
        if (completed != null ? !completed.equals(that.completed) : that.completed != null) return false;
        if (check1 != null ? !check1.equals(that.check1) : that.check1 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (variants != null ? variants.hashCode() : 0);
        result = 31 * result + (completed != null ? completed.hashCode() : 0);
        result = 31 * result + (check1 != null ? check1.hashCode() : 0);
        return result;
    }

    public SurveysEntity(String type, String owner, String name, String categories, String variants, String check, String completed) {
        this.type = type;
        this.owner = owner;
        this.name = name;
        this.categories = categories;
        this.variants = variants;
        this.check1 = check;
        this.completed = completed;
    }

    public SurveysEntity(String type, String owner, String name, List<String> categories, List<String> variants, String check, String completed) {
        this.type = type;
        this.owner = owner;
        this.name = name;
        this.check1 = check;
        this.completed = completed;

        StringBuilder sbcat = new StringBuilder();
        for(String c : categories){
            sbcat.append(c+" ; ");
        }
        this.categories = sbcat.toString(); //.substring(1,sbcat.length()-1);

        StringBuilder sbvar = new StringBuilder();
        for(String v : variants){
            sbvar.append(v+" ; ");
        }
        this.variants = sbvar.toString(); //.substring(1,sbvar.length()-1);
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
                ", completed='" + completed + '\'' +
                ", check1='" + check1 + '\'' +
                '}';
    }
}
