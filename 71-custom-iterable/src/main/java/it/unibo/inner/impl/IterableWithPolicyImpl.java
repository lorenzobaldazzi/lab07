package it.unibo.inner.impl;

import it.unibo.inner.api.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl <T> implements IterableWithPolicy<T> {
    
    private final T[] elements;

    public IterableWithPolicyImpl(final T[] elements){
        this.elements = elements;

    }

    @Override
    public void setIterationPolicy(final Predicate<T> filter){
        //TODO
    }

    private class IteratorImpl implements Iterator<T> {

        private int index = 0;

        

        @Override
        public boolean hasNext() {
            return index < elements.length;
        }

        @Override
        public T next(){
            if(hasNext()){
                return elements[index++];
            }
            throw new NoSuchElementException();
        }

    }

    public Iterator<T> iterator(){
        return new IteratorImpl();
    }
}
