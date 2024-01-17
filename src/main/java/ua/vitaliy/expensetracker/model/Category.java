package ua.vitaliy.expensetracker.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private List<Plan> plans;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(){

    }

    public List<Plan> getPlans(){
        return plans;
    }

    public void addPlan(Plan ... plans){
        if (this.plans == null) {
            this.plans = new ArrayList<>();
        }

        this.plans.addAll(Arrays.asList(plans));
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return categoryId == category.categoryId
                && Objects.equals(categoryName, category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
