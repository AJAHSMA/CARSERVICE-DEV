package com.ajahsma.carservice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author SHARAN A
 */

@MappedSuperclass
public abstract class AbstractIdDomain implements IdDomain {

	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	/**
     * @see AbstractDomain#equals(Object)
     */
    @Override
	public final boolean equals(Object other) {
        if (this == other) {
        	return true;
        }
        
        if (other == null) {
			return false;
		}
        
        if (this.getId() == null) {
        	return false;
        }
        
        if (!(other instanceof IdDomain)) {
        	return false;
        }
        
        IdDomain castOther = (IdDomain) other;
        
        return Long.compare(this.getId(), castOther.getId()) == 0;
    }

    /**
     * @see AbstractDomain#hashCode()
     */
    @Override
	public final int hashCode() {
        if (this.getId() == null) {
        	return System.identityHashCode(this);
        }
        
    	return System.identityHashCode(this.getId());
    }
	
}
