package by.onlineShop.entity;

import java.io.Serializable;

public class Product implements Identifiable, Serializable {
    private long id;
    private long categoryId;
    private long promotionId;
    private String name;
    private String description;
    private double price;
    private boolean status;
    private String photo;
    private long ordersNumber;

    public Product() {
        this.promotionId = 0;
    }

    public Product(long id, long categoryId, String name, String description,
            double price, boolean status, String photo, long ordersNumber) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.photo = photo;
        this.ordersNumber = ordersNumber;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(long promotionId) {
        this.promotionId = promotionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getOrdersNumber() {
        return ordersNumber;
    }

    public void setOrdersNumber(long ordersNumber) {
        this.ordersNumber = ordersNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return id == product.id &&
                categoryId == product.categoryId &&
                promotionId == product.promotionId &&
                name.equals(product.name) &&
                description.equals(product.description) &&
                price == product.price &&
                status == product.status &&
                photo.equals(product.photo) &&
                ordersNumber == product.ordersNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        result = prime * result + (int) categoryId;
        result = prime * result + (int) promotionId;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + (int) price;
        result = prime * result + Boolean.hashCode(status);
        result = prime * result + ((photo == null) ? 0 : photo.hashCode());
        result = prime * result + (int) ordersNumber;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("Product{");
        result.append("id=").append(id);
        result.append(", categoryId=").append(categoryId);
        result.append(", promotionId=").append(promotionId);
        result.append(", name='").append(name).append('\'');
        result.append(", description='").append(description).append('\'');
        result.append(", price=").append(price);
        result.append(", status=").append(status);
        result.append(", photo='").append(photo).append('\'');
        result.append(", ordersNumber=").append(ordersNumber);
        result.append('}');
        return result.toString();
    }
}
