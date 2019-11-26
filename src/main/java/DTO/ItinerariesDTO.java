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
    private double price;
    private String deeplinkUrl;
    private int agents;

    public ItinerariesDTO() {
    }

    public ItinerariesDTO(String outboundLegId, double price, String deeplinkUrl, int agents) {
        this.outboundLegId = outboundLegId;
        this.price = price;
        this.deeplinkUrl = deeplinkUrl;
        this.agents = agents;
    }

    public String getOutboundLegId() {
        return outboundLegId;
    }

    public void setOutboundLegId(String outboundLegId) {
        this.outboundLegId = outboundLegId;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDeeplinkUrl() {
        return deeplinkUrl;
    }

    public void setDeeplinkUrl(String deeplinkUrl) {
        this.deeplinkUrl = deeplinkUrl;
    }

    public int getAgents() {
        return agents;
    }

    public void setAgents(int agents) {
        this.agents = agents;
    }

    @Override
    public String toString() {
        return "ItinerariesDTO{" + "outboundLegId=" + outboundLegId + ", price=" + price + ", deeplinkUrl=" + deeplinkUrl + ", agents=" + agents + '}';
    }

    
    
}
