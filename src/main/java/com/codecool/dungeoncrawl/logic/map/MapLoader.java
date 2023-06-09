package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.gui.Main;
import com.codecool.dungeoncrawl.logic.Objects.Door;
import com.codecool.dungeoncrawl.logic.Objects.Stairs;
import com.codecool.dungeoncrawl.logic.Objects.WinObject;
import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.HealthPotion;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Axe;
import com.codecool.dungeoncrawl.logic.items.Shield;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MapLoader {

    List<String> maps = Arrays.asList("/map.txt","/map2.txt","/map3.txt","/win.txt");

    public GameMap loadMap(Main main,int level) {
        InputStream is = MapLoader.class.getResourceAsStream(maps.get(level));
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        scanner.nextLine(); // empty line

        GameMap map = new GameMap(main,width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'h':
                            cell.setType(CellType.STAIRSDOWN);
                            cell.setEntrance(new Stairs());
                            break;
                        case 'H':
                            cell.setType(CellType.STAIRSUP);
                            cell.setEntrance(new Stairs());
                            break;
                        case 'w':
                            cell.setType(CellType.WIN);
                            cell.setEntrance(new WinObject());
                            break;
                        case 'c':
                            cell.setType(CellType.FAKEWIN);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            new Backbone(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'B':
                            cell.setType(CellType.FLOOR);
                            new Boss(cell);
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new Axe(cell);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Shield(cell);
                            break;
                        case 'd':
                            cell.setType(CellType.FLOOR);
                            new Defender(cell);
                            new HealthPotion(cell);
                            break;
                        case 'l':
                            cell.setType(CellType.CLOSE);
                            cell.setEntrance(new Door());
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            new Ghost(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
