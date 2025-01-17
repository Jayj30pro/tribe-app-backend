package com.savvato.tribeapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(CosignId.class)
public class Cosign {

    @Id
    public Long userIdIssuing;

    @Id
    public Long userIdReceiving;

    @Id
    public Long phraseId;

    public Long getUserIdIssuing() {
        return userIdIssuing;
    }

    public void setUserIdIssuing(Long userId) {
        this.userIdIssuing = userId;
    }

    public Long getUserIdReceiving() {
        return userIdReceiving;
    }

    public void setUserIdReceiving(Long userId) {
        this.userIdReceiving = userId;
    }

    public Long getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(Long phraseId) {
        this.phraseId = phraseId;
    }

}
