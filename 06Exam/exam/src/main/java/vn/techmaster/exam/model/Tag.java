package vn.techmaster.exam.model;
import javax.persistence.*;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Entity(name="tag")
@Table(name="tag")
@Data
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String name;

  @ManyToMany(mappedBy = "tags")
  private List<Product> products=new ArrayList<>();

}
