package de.hofuniversity.iws.server.data.entities;


public interface GenericEntity {
    public Long getId();
    public void setId(Long id);
    public boolean isDetached();
    public void setDetached(boolean detached); 
}