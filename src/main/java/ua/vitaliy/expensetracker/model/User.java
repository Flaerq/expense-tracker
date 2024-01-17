package ua.vitaliy.expensetracker.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    @Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$",
            message="Email only can contain: numbers, letters ._+-% and should match pattern like - 'text@email.com'")
    @NotEmpty(message = "email field should not be empty")
    private String email;

    @Column(name = "password")
    @Length(min = 6, max = 100, message = "Password length should be between 6 and 100")
    private String password;


    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "userOwner")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Plan> plans;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(){

    }

    public void addPlan(Plan ... plans){
        if (this.plans == null){
            this.plans = new ArrayList<>();
        }

        this.plans.addAll(Arrays.asList(plans));
    }

    public void setUserId(int id){
        userId = id;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return userId == user.userId
                && Objects.equals(userName, user.userName)
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password)
                && Objects.equals(creationDate, user.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, email, password, creationDate);
    }
}