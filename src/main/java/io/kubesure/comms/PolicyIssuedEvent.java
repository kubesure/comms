package io.kubesure.comms;

public class PolicyIssuedEvent {
    private long policyNumber;
    private long receiptNumber;
    private long quoteNumber;

    public long getPolicyNumber() {
        return this.policyNumber;
    }

    public void setPolicyNumber(long policyNumber) {
        this.policyNumber = policyNumber;
    }

    public long getReceiptNumber() {
        return this.receiptNumber;
    }

    public void setReceiptNumber(long receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public long getQuoteNumber() {
        return this.quoteNumber;
    }

    public void setQuoteNumber(long quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

}
