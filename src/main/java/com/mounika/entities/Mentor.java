package com.mounika.entities;

public class Mentor {
    private String n;
    public Mentor(String name) {
        this.n = name;
    }
    public String getName() {
        return n;
    }
    public void setName(String name) {
        this.n = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Mentor) {
            return this.n.equals(((Mentor) obj).n);
        }
        return false;
    }
}
