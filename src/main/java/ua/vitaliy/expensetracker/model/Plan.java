package ua.vitaliy.expensetracker.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private int planId;

    @ManyToOne
    @JoinColumn(name  = "user_id")
    private User userOwner;

    @ManyToMany
    @JoinTable(
            name = "category_in_plan",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;


    @Column(name = "plan_name")
    private String planName;

    public Plan(User userOwner, String planName) {
        this.userOwner = userOwner;
        this.planName = planName;
    }

    public Plan(){

    }

    public void addCategory(Category ... categories){
        if (this.categories == null){
            this.categories = new ArrayList<>();
        }

        for (Category category : categories){
            category.addPlan(this);
            this.categories.add(category);
        }
    }

    public List<Category> getCategories() {
        return categories;
    }


    public int getPlanId() {
        return planId;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plan plan)) return false;
        return planId == plan.planId
                && Objects.equals(userOwner, plan.userOwner)
                && Objects.equals(planName, plan.planName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planId, userOwner, planName);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "planId=" + planId +
                ", planName='" + planName + '\'' +
                '}';
    }
}
