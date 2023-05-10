package org.group62.veiw;

import org.group62.controller.*;

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
    Matcher matcher;

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
                System.out.println(newGame());
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.TRADE)) != null)
                System.out.println(setTrade(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.TRADE_LIST)) != null)
                System.out.println(showTradeList(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.TRADE_ACCEPT)) != null)
                System.out.println(tradeAccept(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SET_BLOCK_TEXTURE)) != null)
                System.out.println(setBlockTexture(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SET_RECTANGLE_TEXTURE)) != null)
                System.out.println(setRectangleTexture(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.CLEAR)) != null)
                System.out.println(clear(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.DROP_ROCK)) != null)
                System.out.println(dropRock(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.DROP_TREE)) != null)
                System.out.println(dropTree(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.DROP_BUILDING)) != null)
                System.out.println(dropBuilding(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.DROP_UNIT)) != null)
                System.out.println(dropUnit(matcher));
            else if (Commands.getMatcherMatches(inputCommand, Commands.SHOW_POPULARITY_FACTORS) != null)
                System.out.println(showPopularityFactors());
            else if (Commands.getMatcherMatches(inputCommand, Commands.SHOW_POPULARITY) != null)
                System.out.println(showPopularity());
            else if (Commands.getMatcherMatches(inputCommand, Commands.SHOW_FOOD_LIST) != null)
                System.out.println(showFoodList());
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.FOOD_RATE)) != null)
                System.out.println(setFoodRate(matcher));
            else if (Commands.getMatcherMatches(inputCommand, Commands.FOOD_RATE_SHOW) != null)
                System.out.println(showFoodRate());
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.TAX_RATE)) != null)
                System.out.println(setTaxRate(matcher));
            else if (Commands.getMatcherMatches(inputCommand, Commands.TAX_RATE_SHOW) != null)
                System.out.println(showTaxRate());
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.FEAR_RATE)) != null)
                System.out.println(setFearRate(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SELECT_BUILDING)) != null)
                System.out.println(selectBuilding(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.CREATE_UNIT)) != null)
                System.out.println(creatUnit(matcher));
            else if (Commands.getMatcherMatches(inputCommand, Commands.REPAIR) != null)
                System.out.println(repair());
            else if (Commands.getMatcherMatches(inputCommand, Commands.NEXT_TURN) != null)
                System.out.println(nextTurn());
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SELECT_UNIT)) != null)
                System.out.println(selectUnit(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.MOVE_UNIT)) != null)
                System.out.println(moveUnit(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.PATROL_UNIT)) != null)
                System.out.println(patrolUnit(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SET_STATE)) != null)
                System.out.println(setState(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.ATTACK)) != null)
                System.out.println(attack(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.ATTACK_COORDINATES)) != null)
                System.out.println(attackWithArrows(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.POUR_OIL)) != null)
                System.out.println(pourOil(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.DIG_TUNNEL)) != null)
                System.out.println(digTunnel(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.BUILD)) != null)
                System.out.println(buidEquipment(matcher));
            else if (Commands.getMatcherMatches(inputCommand, Commands.DISBAND_UNIT) != null)
                System.out.println(disbandUnit());
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SHOW_MAP)) != null)
                System.out.println(showMap(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.SHOW_DETAILS)) != null)
                System.out.println(showDetails(matcher));
            else if ((matcher = Commands.getMatcherMatches(inputCommand, Commands.MAP_MOVE)) != null)
                System.out.println(moveMap(matcher));
            else if (Commands.getMatcherMatches(inputCommand, Commands.SHOW_PRICE_LIST) != null)
                System.out.println(showPriceList());
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

    private String showPriceList() {
        return null;
    }

    private String moveMap(Matcher matcher) {
        return null;
    }

    private String showDetails(Matcher matcher) {
        return null;
    }

    private String showMap(Matcher matcher) {
        return null;
    }

    private String disbandUnit() {
        return null;
    }

    private String buidEquipment(Matcher matcher) {
        return null;
    }

    private String digTunnel(Matcher matcher) {
        return null;
    }

    private String pourOil(Matcher matcher) {
        return null;
    }

    private String attackWithArrows(Matcher matcher) {
        return null;
    }

    private String attack(Matcher matcher) {
        return null;
    }

    private String setState(Matcher matcher) {
        return null;
    }

    private String patrolUnit(Matcher matcher) {
        return null;
    }

    private String moveUnit(Matcher matcher) {
        return null;
    }

    private String selectUnit(Matcher matcher) {
        return null;
    }

    private String nextTurn() {
        return null;
    }

    private String repair() {
        return null;
    }

    private String creatUnit(Matcher matcher) {
        return null;
    }

    private String selectBuilding(Matcher matcher) {
        return null;
    }

    private String setFearRate(Matcher matcher) {
        return null;
    }

    private String showTaxRate() {
        return null;
    }

    private String setTaxRate(Matcher matcher) {
        return null;
    }

    private String showFoodRate() {
        return null;
    }

    private String setFoodRate(Matcher matcher) {
        return null;
    }

    private String showFoodList() {
        return null;
    }

    private String showPopularity() {
        return null;
    }

    private String showPopularityFactors() {
        return null;
    }

    private String dropUnit(Matcher matcher) {
        return null;
    }

    private String dropBuilding(Matcher matcher) {
        return null;
    }

    private String dropTree(Matcher matcher) {
        return null;
    }

    private String dropRock(Matcher matcher) {
        return null;
    }

    private String clear(Matcher matcher) {
        return null;
    }

    private String setRectangleTexture(Matcher matcher) {
        return null;
    }

    private String setBlockTexture(Matcher matcher) {
        return null;
    }

    private String tradeAccept(Matcher matcher) {
        return null;
    }

    private String showTradeList(Matcher matcher) {
        return null;
    }

    private String setTrade(Matcher matcher) {
        return null;
    }

    private String newGame() {
        return null;
    }
}
