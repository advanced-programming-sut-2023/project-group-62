package org.group62.veiw;

import org.group62.controller.*;
import org.group62.model.Governance;
import org.group62.model.Play;
import org.group62.model.Resource;
import org.group62.model.User;

import java.util.Scanner;
import java.util.regex.Matcher;


public class GameMenu {
    private TradeController tradeController;
    private CheaterController cheaterController;
    private GovernanceController governanceController;
    private BuildingController buildingController;
    private TurnsController turnsController;
    private UnitsController unitsController;
    private MapMenuController mapMenuController;
    private MarketMenuController marketMenuController;
    private GameMenuController gameMenuController;
    private User currentUser;
    Matcher matcher;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public GameMenu(GameMenuController gameMenuController) {
        this.gameMenuController = gameMenuController;
        tradeController = new TradeController();
        cheaterController = new CheaterController();
        governanceController = new GovernanceController();
        buildingController = new BuildingController();
        turnsController = new TurnsController();
        unitsController = new UnitsController();
        mapMenuController = new MapMenuController();
        marketMenuController = new MarketMenuController();
    }

    public void run(Scanner scanner) {
        while (true) {
            String inputCommand = scanner.nextLine();
            if (Commands.getMatcherMatches(inputCommand, Commands.NEW_GAME) != null)
                newGame(scanner);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.TRADE)) != null)
                setTrade(matcher);
            else if ((Commands.getMatcherMatches(inputCommand, Commands.TRADE_LIST)) != null)
                showTradeList();
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.TRADE_ACCEPT)) != null)
                tradeAccept(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SET_BLOCK_TEXTURE)) != null)
                setBlockTexture(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SET_RECTANGLE_TEXTURE)) != null)
                setRectangleTexture(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.CLEAR)) != null)
                clear(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.DROP_ROCK)) != null)
                dropRock(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.DROP_TREE)) != null)
                dropTree(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.DROP_BUILDING)) != null)
                dropBuilding(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.DROP_UNIT)) != null)
                dropUnit(matcher);
            else if (Commands.getMatcherMatches(inputCommand, Commands.SHOW_POPULARITY_FACTORS) != null)
                showPopularityFactors();
            else if (Commands.getMatcherMatches(inputCommand, Commands.SHOW_POPULARITY) != null)
                showPopularity();
            else if (Commands.getMatcherMatches(inputCommand, Commands.SHOW_FOOD_LIST) != null)
                showFoodList();
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.FOOD_RATE)) != null)
                setFoodRate(matcher);
            else if (Commands.getMatcherMatches(inputCommand, Commands.FOOD_RATE_SHOW) != null)
                showFoodRate();
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.TAX_RATE)) != null)
                setTaxRate(matcher);
            else if (Commands.getMatcherMatches(inputCommand, Commands.TAX_RATE_SHOW) != null)
                showTaxRate();
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.FEAR_RATE)) != null)
                setFearRate(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SELECT_BUILDING)) != null)
                selectBuilding(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.CREATE_UNIT)) != null)
                creatUnit(matcher);
            else if (Commands.getMatcherMatches(inputCommand, Commands.REPAIR) != null)
                repair();
            else if (Commands.getMatcherMatches(inputCommand, Commands.NEXT_TURN) != null)
                nextTurn();
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SELECT_UNIT)) != null)
                selectUnit(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.MOVE_UNIT)) != null)
                moveUnit(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.PATROL_UNIT)) != null)
                patrolUnit(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SET_STATE)) != null)
                setState(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.ATTACK)) != null)
                attack(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.ATTACK_COORDINATES)) != null)
                attackWithArrows(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.POUR_OIL)) != null)
                pourOil(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.DIG_TUNNEL)) != null)
                digTunnel(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.BUILD)) != null)
                buidEquipment(matcher);
            else if (Commands.getMatcherMatches(inputCommand, Commands.DISBAND_UNIT) != null)
                disbandUnit();
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SHOW_MAP)) != null)
                showMap(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SHOW_DETAILS)) != null)
                showDetails(matcher);
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.MAP_MOVE)) != null)
                moveMap(matcher);
            else if (Commands.getMatcherMatches(inputCommand, Commands.SHOW_PRICE_LIST) != null)
                showPriceList();
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.BUY)) != null)
                System.out.println(buyItem(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SELL)) != null)
                System.out.println(sellItem(matcher));
            else
                System.out.println("Invalid command!");
        }
    }

    private String sellItem(Matcher matcher) {
        return null;
    }

    private String buyItem(Matcher matcher) {
        return null;
    }

    private void showPriceList() {
        System.out.println(Resource.WOOD + "\tBuyPrice: " + Resource.WOOD.getBuyPrice() + "\tSellPrice: " +
                Resource.WOOD.getSellPrice() + "\tYou have " + Play.getCurrentGovernance().getResources().get(Resource.WOOD) + "items.");
        System.out.println(Resource.STONE + "\tBuyPrice: " + Resource.STONE.getBuyPrice() + "\tSellPrice: " +
                Resource.STONE.getSellPrice() + "\tYou have " + Play.getCurrentGovernance().getResources().get(Resource.STONE) + "items.");
        System.out.println(Resource.PITCH + "\tBuyPrice: " + Resource.PITCH.getBuyPrice() + "\tSellPrice: " +
                Resource.PITCH.getSellPrice() + "\tYou have " + Play.getCurrentGovernance().getResources().get(Resource.PITCH) + "items.");
        System.out.println(Resource.BEER + "\tBuyPrice: " + Resource.BEER.getBuyPrice() + "\tSellPrice: " +
                Resource.BEER.getSellPrice() + "\tYou have " + Play.getCurrentGovernance().getResources().get(Resource.BEER) + "items.");
        System.out.println(Resource.IRON + "\tBuyPrice: " + Resource.IRON.getBuyPrice() + "\tSellPrice: " +
                Resource.IRON.getSellPrice() + "\tYou have " + Play.getCurrentGovernance().getResources().get(Resource.IRON) + "items.");
        System.out.println(Resource.BARLEY + "\tBuyPrice: " + Resource.BARLEY.getBuyPrice() + "\tSellPrice: " +
                Resource.BARLEY.getSellPrice() + "\tYou have " + Play.getCurrentGovernance().getResources().get(Resource.BARLEY) + "items.");
        System.out.println(Resource.FLOUR + "\tBuyPrice: " + Resource.FLOUR.getBuyPrice() + "\tSellPrice: " +
                Resource.FLOUR.getSellPrice() + "\tYou have " + Play.getCurrentGovernance().getResources().get(Resource.FLOUR) + "items.");
        System.out.println(Resource.WHEAT + "\tBuyPrice: " + Resource.WHEAT.getBuyPrice() + "\tSellPrice: " +
                Resource.WHEAT.getSellPrice() + "\tYou have " + Play.getCurrentGovernance().getResources().get(Resource.WHEAT) + "items.");
    }

    private void moveMap(Matcher matcher) {
        //TODO
        //mapMenuController.moveMap();

    }

    private void showDetails(Matcher matcher) {
        String xCoordinate = matcher.group("xCoordinates");
        String yCoordinate = matcher.group("yCoordinates");
        System.out.println(mapMenuController.showMap(Integer.parseInt(xCoordinate),Integer.parseInt(yCoordinate)));
    }

    private void showMap(Matcher matcher) {
        String xCoordinate = matcher.group("xCoordinates");
        String yCoordinate = matcher.group("yCoordinates");
        System.out.println(mapMenuController.showMap(Integer.parseInt(xCoordinate),Integer.parseInt(yCoordinate)));
    }

    private void disbandUnit() {
        //TODO
    }

    private void buidEquipment(Matcher matcher) {
        //TODO
    }

    private void digTunnel(Matcher matcher) {
        //TODO
    }

    private void pourOil(Matcher matcher) {
        //TODO
    }

    private void attackWithArrows(Matcher matcher) {
        //TODO
    }

    private void attack(Matcher matcher) {
        //TODO
    }

    private void setState(Matcher matcher) {
        //TODO
    }

    private void patrolUnit(Matcher matcher) {
        //TODO
    }

    private void moveUnit(Matcher matcher) {
        //TODO
    }

    private void selectUnit(Matcher matcher) {
        //TODO
    }

    private void nextTurn() {
        //TODO
        turnsController.nextTurn();
    }

    private void repair() {
        System.out.println(buildingController.repair());
    }

    private void creatUnit(Matcher matcher) {
        String type = matcher.group("type");
        String count = matcher.group("count");
        System.out.println(buildingController.createUnit(type,Integer.parseInt(count)));
    }

    private void selectBuilding(Matcher matcher) {
        String xCoordinates = matcher.group("xCoordinates");
        String yCoordinates = matcher.group("yCoordinates");
        System.out.println(buildingController.selectBuilding(Integer.parseInt(xCoordinates),Integer.parseInt(yCoordinates)));
    }

    private void setFearRate(Matcher matcher) {
        String rateNumber = matcher.group("rateNumber");
        System.out.println(governanceController.setFearRate(Integer.parseInt(rateNumber)));
    }

    private void showTaxRate() {
        System.out.println(governanceController.showTaxRate());
    }

    private void setTaxRate(Matcher matcher) {
        String rateNumber = matcher.group("rateNumber");
        System.out.println(governanceController.setTaxRate(Integer.parseInt(rateNumber)));
    }

    private void showFoodRate() {
        System.out.println(governanceController.showFoodRate());
    }

    private void setFoodRate(Matcher matcher) {
        String rateNumber = matcher.group("rateNumber");
        governanceController.setFoodRate(Integer.parseInt(rateNumber));
    }

    private void showFoodList() {
        governanceController.showFoodList();
    }

    private void showPopularity() {
        governanceController.showPopularity();
    }

    private void showPopularityFactors() {
        governanceController.showPopularityFactors();
    }

    private void dropUnit(Matcher matcher) {
        String xCoordinates = matcher.group("xCoordinates");
        String yCoordinates = matcher.group("yCoordinates");
        String type = matcher.group("type");
        String count = matcher.group("count");
        String color = matcher.group("color");
        Governance governance = cheaterController.findGovernanceByColor(color);
        System.out.println(cheaterController.dropUnit(Integer.parseInt(xCoordinates),
                Integer.parseInt(yCoordinates),type,Integer.parseInt(count),governance));
    }

    private void dropBuilding(Matcher matcher) {
        String xCoordinates = matcher.group("xCoordinates");
        String yCoordinates = matcher.group("yCoordinates");
        String type = matcher.group("type");
        String color = matcher.group("color");
        Governance governance = cheaterController.findGovernanceByColor(color);
        System.out.println(cheaterController.dropBuilding(Integer.parseInt(xCoordinates),Integer.parseInt(yCoordinates),
                type,governance));
    }

    private void dropTree(Matcher matcher) {
        String xCoordinates = matcher.group("xCoordinates");
        String yCoordinates = matcher.group("yCoordinates");
        String type = matcher.group("type");
        System.out.println(cheaterController.dropTree(Integer.parseInt(xCoordinates),Integer.parseInt(yCoordinates),
                type));
    }

    private void dropRock(Matcher matcher) {
        String xCoordinates = matcher.group("xCoordinates");
        String yCoordinates = matcher.group("yCoordinates");
        String direction = matcher.group("direction");
        System.out.println(cheaterController.dropRock(Integer.parseInt(xCoordinates),Integer.parseInt(yCoordinates),direction));
    }

    private void clear(Matcher matcher) {
        String xCoordinates = matcher.group("xCoordinates");
        String yCoordinates = matcher.group("yCoordinates");
        System.out.println(cheaterController.clear(Integer.parseInt(xCoordinates),Integer.parseInt(yCoordinates)));
    }

    private void setRectangleTexture(Matcher matcher) {
        String x1Coordinates = matcher.group("x1Coordinates");
        String y1Coordinates = matcher.group("y1Coordinates");
        String x2Coordinates = matcher.group("x2Coordinates");
        String y2Coordinates = matcher.group("y2Coordinates");
        String type = matcher.group("type");
        System.out.println(cheaterController.setTexture(Integer.parseInt(x1Coordinates),Integer.parseInt(y1Coordinates),
                Integer.parseInt(x2Coordinates),Integer.parseInt(y2Coordinates),type));
    }

    private void setBlockTexture(Matcher matcher) {
        String xCoordinates = matcher.group("xCoordinates");
        String yCoordinates = matcher.group("yCoordinates");
        String type = matcher.group("type");
        System.out.println(cheaterController.setTexture(Integer.parseInt(xCoordinates),
                Integer.parseInt(yCoordinates),type));
    }

    private void tradeAccept(Matcher matcher) {
        int id = Integer.parseInt(matcher.group("id"));
        String message = matcher.group("message");
        System.out.println(tradeController.acceptTrade(id,message));
    }

    private void showTradeList() {
        System.out.println(tradeController.showTradeList());
    }

    private void setTrade(Matcher matcher) {
        String resourceType = matcher.group("resourceType");
        String resourceAmount = matcher.group("resourceAmount");
        String price = matcher.group("price");
        String message = matcher.group("message");
        System.out.println(tradeController.addTrade(resourceType,Integer.parseInt(resourceAmount),
                Integer.parseInt(price),message));
    }

    private void newGame(Scanner scanner) {
        System.out.println("Please enter user 3 usernames to play with them: ");
        String playUsernames = scanner.nextLine();
        String[] players = playUsernames.split(" ");
        String message = gameMenuController.newGame(players,currentUser);
        System.out.println(message);
    }
}
