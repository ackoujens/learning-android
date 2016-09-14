package be.ackoujens.androidgameframework;

import java.util.ArrayList;
import java.util.List;

/**
 * Pool
 */
public class Pool<T> {
    /**
     * Generic
     * returns new object with generic type of the Pool.PoolObjectFactory instance
     * @param <T>
     */
    public interface PoolObjectFactory<T> {
        public T createObject();
    }

    private final List<T> freeObjects;
    private final PoolObjectFactory<T> factory;
    private final int maxSize;

    /**
     * Pool
     * - takes PoolObjectFactory & max number of objects it should store
     * - creates ArrayList with capacity set to max number of objects
     * @param factory
     * @param maxSize
     */
    public Pool(PoolObjectFactory<T> factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        this.freeObjects = new ArrayList<T>(maxSize);
    }

    /**
     * newObject
     * - handing a brand-new instance of thz type held by the PoolObjectFactory.newObject() method
     * - or returning a pooled instance in case there is one in the freeObjectsArrayList
     * @return pooled instance
     */
    public T newObject() {
        T object = null;
        if (freeObjects.isEmpty()) object = factory.createObject();
        else object = freeObjects.remove(freeObjects.size() - 1);
        return object;
    }

    /**
     * free
     * - let's us reinsert objects that we no longer use
     * - if full, it is not added
     * @param object
     */
    public void free(T object) {
        if (freeObjects.size() < maxSize) freeObjects.add(object);
    }
}

/*
// Usage example
PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() {
    @Override
    public TouchEvent createObject() {
        return new TouchEvent();
    }
};
Pool<TouchEvent> touchEventPool = new Pool<TouchEvent>(factory, 50);
TouchEvent touchEvent = touchEventPool.newObject();
... do something here ...
touchEventPool.free(touchEvent);
*/

/*
- PoolObjectFactory creates TouchEvent instances
- instantiate the Pool by telling it to use our factory, max storage of 50 TouchEvents
- when we want new TouchEvent object from pool -> call Pool's newObject method
    - initially, the pool is empty -> ask factory to create brand-new TouchEvent instance

- when we don't need the instance anymore -> reinsert it into the Pool by calling free()

- next time we call newObject() -> get same TouchEvent instance and recycle it to avoid problems with the garbage collector

! be carefull to fully reinitialize reused objects when they are fetched from the Pool
*/