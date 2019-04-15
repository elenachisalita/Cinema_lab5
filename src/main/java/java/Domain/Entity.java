package java.Domain;

import java.util.Objects;

public abstract class Entity {


    private String id;


    Entity(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Entity entity = (Entity) obj;

        return Objects.equals(id, entity.id);
    }

    public String getId() {
        return id;
    }

}
