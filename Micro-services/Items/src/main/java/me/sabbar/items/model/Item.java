package me.sabbar.items.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;


@Document(collection = "item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private String id;

        //@Column(name = "name")
        private String name;

        //@Column(name = "description")
        private String description;

        private Long userId;
}
