package it.unibo.inner.impl;

import it.unibo.inner.api.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl <T> implements IterableWithPolicy<T> {
    
    private final T[] elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] elements){
        this(
            elements,
            new Predicate<T>() {
                @Override
                public boolean test(T elem){
                    return true;
                }
            }
        );

    }


    public IterableWithPolicyImpl(final T[] elements, Predicate<T> predicate){
        this.elements = elements;
        this.filter = predicate;

    }


    public void setIterationPolicy(final Predicate<T> filter){
        this.filter = filter;
    }

    public Iterator<T> iterator(){
        return new IteratorImpl();
    }
    
    private class IteratorImpl implements Iterator<T> {

        private int index = 0;

        

        @Override
        public boolean hasNext() {
            while(index < elements.length){
                if(filter.test(elements[index])){
                    return true;
                }
                index++;
            }
            return false;
            
        }

        @Override
        public T next(){
            if(hasNext()){
                return elements[index++];
            }
            throw new NoSuchElementException();
        }

    }



    
}
