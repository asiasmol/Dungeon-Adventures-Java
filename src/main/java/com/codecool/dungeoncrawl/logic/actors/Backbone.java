package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.map.CellType;
import com.codecool.dungeoncrawl.logic.map.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Backbone extends Actor {

    Random random = new Random();

    private static final List<CellType> GROUND = List.of(CellType.FLOOR);

    public Backbone(Cell cell) {
        super(cell);
        health = 50;
        damage = 5;
    }

    @Override
    public void move() {
        GameMap map = cell.getGameMap();
        Player player = map.getPlayer();
        Cell playerCell = player.cell;

        List<int[]> steps = getSteps();
        List<int[]> filteredSteps = steps.stream().filter(d -> cell.hasNeighbor(d[0], d[1]))
                        .filter(d -> GROUND.contains(cell.getNeighbor(d[0], d[1]).getType()))
                        .filter(d -> playerCell.equals(cell.getNeighbor(d[0], d[1]))
                                || cell.getNeighbor(d[0], d[1]).getActor() == null)
                        .collect(Collectors.toList());

        if (filteredSteps.size() > 0){
            int[] step = filteredSteps.get(random.nextInt(filteredSteps.size()));
            Cell next = cell.getNeighbor(step[0], step[1]);
            if (next.getActor() == null)  changeCell(step[0], step[1]);
            else fight(player);
        }
    }


    private List<int[]> getSteps() {
        List <int[]> trackingSteps = getTrackingSteps();
        if (trackingSteps.size() > 0) {
            return trackingSteps;
        } else {
            return List.of(new int[]{-1, 0}, new int[]{1, 0}, new int[]{0, -1}, new int[]{0, 1});
        }
    }

    private List<int[]> getTrackingSteps() {
        int numberOfCellsAroundToSearch = 3;
        int x = cell.getX();
        int y = cell.getY();
        for (int i = x - numberOfCellsAroundToSearch; i <= x + numberOfCellsAroundToSearch ; i++) {
            for (int j = y - numberOfCellsAroundToSearch; j <= y + numberOfCellsAroundToSearch; j++) {
                if (cell.getGameMap().areCoordsOnMap(i, j)
                        && cell.getGameMap().isPlayerOnCoords(i, j)){
                    // dx and dy must be 1, 0 or -1 and correspond to the direction from backbone to player
                    int dx = (int) Math.signum(i - x);
                    int dy = (int) Math.signum(j - y);
                    // one of {dx, dy} must be 0 because backbone cannot move diagonally
                    // if this is the case, then {dx, dy} is the step we were looking for
                    if (dx * dy == 0) return List.of(new int[] {dx, dy});
                    // if both dx and dy are not 0, we have two possible steps {dx, 0} and {0, dy}
                    else return List.of(new int[]{dx, 0}, new int[] {0, dy});
                }
            }
        }
        return new ArrayList<>();
    }

    @Override
    public String getTileName() {
        return "backbone";
    }
}
