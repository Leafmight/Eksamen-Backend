/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 *  listOutboundLegId = listItineraries.getJSONObject(i).get("OutboundLegId").toString();
             listInboundLegId = listItineraries.getJSONObject(i).get("InboundLegId").toString();
            for (int j = 0; j < listPricingOptions.length(); j++) {
                System.out.println(listPricingOptions.getJSONObject(j).get("Price"));
                System.out.println(listPricingOptions.getJSONObject(j).get("DeeplinkUrl"));
 * @author jacobfolkehildebrandt
 */
public class ItinerariesDTO {
    private String outboundLegId;
    private String inboundLegId;
    private long price;
    private String deeplinkUrl;

    public ItinerariesDTO() {
    }

    public ItinerariesDTO(String outboundLegId, String inboundLegId, long price, String deeplinkUrl) {
        this.outboundLegId = outboundLegId;
        this.inboundLegId = inboundLegId;
        this.price = price;
        this.deeplinkUrl = deeplinkUrl;
    }

    public String getOutboundLegId() {
        return outboundLegId;
    }

    public void setOutboundLegId(String outboundLegId) {
        this.outboundLegId = outboundLegId;
    }

    public String getInboundLegId() {
        return inboundLegId;
    }

    public void setInboundLegId(String inboundLegId) {
        this.inboundLegId = inboundLegId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDeeplinkUrl() {
        return deeplinkUrl;
    }

    public void setDeeplinkUrl(String deeplinkUrl) {
        this.deeplinkUrl = deeplinkUrl;
    }

    @Override
    public String toString() {
        return "ItinerariesDTO{" + "outboundLegId=" + outboundLegId + ", inboundLegId=" + inboundLegId + ", price=" + price + ", deeplinkUrl=" + deeplinkUrl + '}';
    }
     
}
