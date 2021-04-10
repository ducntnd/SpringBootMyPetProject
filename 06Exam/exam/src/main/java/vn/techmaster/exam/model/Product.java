package vn.techmaster.exam.model;

import javax.persistence.*;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity(name="product")
@Table(name="product")
@Data
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private Category category;

  @ManyToMany
  @JoinTable(name = "product_tag",
  joinColumns =@JoinColumn(name = "product_id"),
  inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private Set<Tag> tags=new HashSet<>();

  public void addTag(Tag tag){
    tags.add(tag);
    tag.getProducts().add(this);
  }

  public void removeTag(Tag tag){
    tags.remove(tag);
    tag.getProducts().remove(this);
  }
}
