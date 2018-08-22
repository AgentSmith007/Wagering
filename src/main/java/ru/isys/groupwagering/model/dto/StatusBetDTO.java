package ru.isys.groupwagering.model.dto;

public class StatusBetDTO {

    private boolean draft;
    private boolean open;
    private boolean done;
    private boolean finished;
    private boolean cancelled;

    WageringStatusDTO wageringStatusDTO;

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public WageringStatusDTO getWageringStatusDTO() {
        return wageringStatusDTO;
    }

    public void setWageringStatusDTO(WageringStatusDTO wageringStatusDTO) {
        this.wageringStatusDTO = wageringStatusDTO;
    }

    public StatusBetDTO(boolean draft, boolean open, boolean done, boolean finished, boolean cancelled) {
        this.draft = draft;
        this.open = open;
        this.done = done;
        this.finished = finished;
        this.cancelled = cancelled;
    }

    public StatusBetDTO(boolean draft, boolean open, boolean done, boolean finished, boolean cancelled,
                        WageringStatusDTO wageringStatusDTO) {
        this.draft = draft;
        this.open = open;
        this.done = done;
        this.finished = finished;
        this.cancelled = cancelled;
        this.wageringStatusDTO = wageringStatusDTO;
    }

    public StatusBetDTO(){}
}
