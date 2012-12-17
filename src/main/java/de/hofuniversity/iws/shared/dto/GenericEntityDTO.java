package de.hofuniversity.iws.shared.dto;


public interface GenericEntityDTO {
    public Long getId();
    public void setId(Long id);
    public boolean isDetached();
    public void setDetached(boolean detached); 
}