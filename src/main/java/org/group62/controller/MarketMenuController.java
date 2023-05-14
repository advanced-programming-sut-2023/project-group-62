package org.group62.controller;

import org.group62.model.Play;
import org.group62.model.Resource;

public class MarketMenuController {

    public String buy(String itemName,int amount){
        Resource resource;
        if(amount<=0)
            return "invalid amount";
        else if ((resource = Resource.getResourceByName(itemName)) == null)
            return "invalid item name";
        else if (Play.getCurrentGovernance().getCapacityOfResource() >= Play.getCurrentGovernance().getAmountOfResource() + amount)
            return "your capacity is not enough";
        else {
            Play.getCurrentGovernance().addResource(resource,amount);
            Play.getCurrentGovernance().setGold(Play.getCurrentGovernance().getGold() - resource.getBuyPrice());
            return "sell item was successful";
        }
    }
    public String sell(String itemName,int amount){
        Resource resource;
        if(amount<=0)
            return "invalid amount";
        else if ((resource = Resource.getResourceByName(itemName)) == null)
            return "invalid item name";
        else if (!Play.getCurrentGovernance().getResources().containsKey(resource))
            return "you don't have this resource";
        else if (Play.getCurrentGovernance().getResources().get(resource)<amount)
            return "you don't have enough resource";
        else {
            Play.getCurrentGovernance().decreaseResource(resource,amount);
            Play.getCurrentGovernance().setGold(Play.getCurrentGovernance().getGold() + resource.getSellPrice());
            return "sell item was successful";
        }
    }
}
