package com.crmsys.po;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by Paosin Von Scarlet on 2017/9/28.
 */
@Entity
@Table(name = "crm_city", schema = "crm")
public class City {
    private Integer id;
    private String code;
    private String name;
    private String firstLetter;
    private Integer level;
    private City parent;
    private Set<City> cityies;
    private Set<Client> clients;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "first_letter")
    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (id != null ? !id.equals(city.id) : city.id != null) return false;
        if (code != null ? !code.equals(city.code) : city.code != null) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        if (firstLetter != null ? !firstLetter.equals(city.firstLetter) : city.firstLetter != null)
            return false;
        if (level != null ? !level.equals(city.level) : city.level != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (firstLetter != null ? firstLetter.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public City getParent() {
        return parent;
    }

    public void setParent(City parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
    public Set<City> getCityies() {
        return cityies;
    }

    public void setCityies(Set<City> cityies) {
        this.cityies = cityies;
    }

    @OneToMany(mappedBy = "city")
    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
