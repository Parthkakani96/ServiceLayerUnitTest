package com.weetechsolution.servicelayerunittest.model;

import javax.persistence.*;

@Entity
@Table
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private boolean published;

    @Column
    private boolean inStore;

    @Column
    private String author;

    @Column
    private boolean inPrint;

    public Tutorial() {}

    public Tutorial(String title, String description, boolean published, boolean inStore, String author, boolean inPrint) {
        super();
        this.title = title;
        this.description = description;
        this.published = published;
        this.inStore = inStore;
        this.author = author;
        this.inPrint = inPrint;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isInStore() {
        return inStore;
    }

    public void setInStore(boolean inStore) {
        this.inStore = inStore;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isInPrint() {
        return inPrint;
    }

    public void setInPrint(boolean inPrint) {
        this.inPrint = inPrint;
    }

    @Override
    public String toString() {
        return "Tutorial{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", published=" + published +
                ", inStore=" + inStore +
                ", author='" + author + '\'' +
                ", inPrint=" + inPrint +
                '}';
    }
}
