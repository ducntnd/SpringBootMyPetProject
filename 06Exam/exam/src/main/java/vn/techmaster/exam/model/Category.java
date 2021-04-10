package vn.techmaster.exam.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Entity(name="category")
@Table(name="category")
@Data
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String name;

  @OneToMany(cascade=CascadeType.ALL)
  @JoinColumn(name = "category_id")
  List<Product> products=new ArrayList<>();

  public void addProduct(Product product){
    products.add(product);
    product.setCategory(this);
  }

  public void removeProduct(Product product){
    products.remove(product);
    product.setCategory(null);
  }
}
