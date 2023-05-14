package org.group62.controller;

import org.group62.model.*;

public class TradeController {
    public String addTrade(String resourceType, int resourceAmount, int price, String message) {
        Resource resource;
        if ((resource = Resource.getResourceByName(resourceType)) == null)
            return "invalid resource type";
        else if (resourceAmount < 0)
            return "invalid amount";
        else if (Play.getCurrentGovernance().getResources().get(resource) < resourceAmount)
            return "you don't have enough resource";
        else if (price < 0)
            return "invalid price";
        else {
            Trade trade = new Trade(Play.getCurrentGovernance(), resource, resourceAmount, price, message)
            StrongHold.getCurrentPlay().addTrade(trade);
            Play.getCurrentGovernance().addTradeToTradeHistory(trade);
            return "add trade was successful";
        }
    }

    public String showTradeList() {
        String output = "trades:\n";
        for (Trade trade : StrongHold.getCurrentPlay().getTrades()) {
            output = output + String.format("id: %d type: %s amount: %d prise: %d message: %s\n",
                    StrongHold.getCurrentPlay().getTrades().indexOf(trade), trade.getResourceType(),
                    trade.getResourceAmount(), trade.getPrice(), trade.getMessage());
        }
        return output;
    }

    public String acceptTrade(int id, String message) {
        if (id >= StrongHold.getCurrentPlay().getTrades().size())
            return "invalid id";
        if (StrongHold.getCurrentPlay().getTrades().get(id).getOwner().equals(Play.getCurrentGovernance()))
            return "you can't accept your trade";
        else {
            Trade trade = StrongHold.getCurrentPlay().getTrades().get(id);
            trade.setAcceptedGovernance(Play.getCurrentGovernance());
            trade.setThisUsed(true);
            Play.getCurrentGovernance().addTradeToTradeHistory(trade);
            trade.getOwner().addResource(trade.getResourceType(), trade.getResourceAmount());
            trade.getAcceptedGovernance().decreaseResource(trade.getResourceType(), trade.getResourceAmount());
            trade.getOwner().setGold(trade.getOwner().getGold() + trade.getPrice());
            trade.getAcceptedGovernance().setGold(trade.getAcceptedGovernance().getGold() - trade.getPrice());
            return "trade was accepted";
        }
    }

    public String showTradeHistory() {
        String output = "trade history:\n";
        for (Trade trade : Play.getCurrentGovernance().getTradesHistory()) {
            output = output + "is this used: " + trade.isThisUsed() +
                    String.format(" type: %s amount: %d prise: %d message: %s\n",
                    trade.getResourceType(), trade.getResourceAmount(), trade.getPrice(), trade.getMessage());
        }
        return output;
    }
}
