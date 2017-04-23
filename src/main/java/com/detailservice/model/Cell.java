package com.detailservice.model;

import javax.persistence.*;

@Entity
@Table(name = "Cell")
public class Cell {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "cell_id")
    private Long cellId;

    @Column(name = "ctn")
    private Long ctn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getCellId() {
        return cellId;
    }

    public void setCellId(Long cellId) {
        this.cellId = cellId;
    }

    public Long getCtn() {
        return ctn;
    }

    public void setCtn(Long ctn) {
        this.ctn = ctn;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "id=" + id +
                ", cellId=" + cellId +
                ", ctn=" + ctn +
                '}';
    }
}
