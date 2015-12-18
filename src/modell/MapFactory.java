package modell;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import modell.tile.PathTile;
import modell.tile.Tile;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Class:       TileMap
 *
 * Author:      Erik Moström
 * cs-user:     dv14emm
 * Date:        2015-12-01
 */
public class MapFactory {
    private NodeList mapList;
    private ArrayList<String> mapNames;

    /*Will contain all types of tiles in order to save time when needing
    * the same class several times. */
    private Hashtable<String, Class<?>> tileTypes;

    /**
     * Creates a new instance of MapFactory. The factory will be able to return
     * Map instances containing information of a specific level. This
     * constructor is for when the map file is within the project structure.
     *
     * @param mapFile the name of the file containing the maps.
     * @throws IOException when something goes wrong reading the map/schema file
     * @throws SAXException when something goes wrong when parsing either of the
     * files
     */
    public MapFactory(String mapFile) throws IOException, SAXException {
        validateMap(new StreamSource( getClass().getResourceAsStream(mapFile) ));
        mapList = getMaps(new InputSource( getClass().getResourceAsStream(mapFile)));
        mapNames = collectMapNames();
        tileTypes = new Hashtable<>();
    }

    /**
     * Creates a new instance of MapFactory. The factory will be able to return
     * Map instances containing information of a specific level. This
     * constructor is for when the map file is outside of the compiled jar-file.
     *
     * @param mapFilePath a URL to the file containing the maps
     * @throws IOException when something goes wrong reading the map/schema file
     * @throws SAXException when something goes wrong when parsing either of the
     * files
     */
    public MapFactory(URL mapFilePath) throws IOException, SAXException {
        validateMap(new StreamSource( mapFilePath.openStream() ));
        mapList = getMaps(new InputSource( mapFilePath.openStream() ));
        mapNames = collectMapNames();
        tileTypes = new Hashtable<>();
    }

    /**
     * Method for reading all the maps in a map-file and putting them into a
     * NodeList.
     *
     * @param maps InputSource constructed from the map-file
     * @return a NodeList containing all the maps of the map-file
     * @throws IOException when unable to read all the maps.
     */
    private NodeList getMaps(InputSource maps) throws IOException {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(maps);
            Element data = doc.getDocumentElement();
            data.normalize();
            return doc.getElementsByTagName("map");
        } catch (ParserConfigurationException e) {
            throw new IOException("Unable to configure parser");
        } catch (SAXException e) {
            throw new IOException("Not correct format");
        }
    }

    /**
     * Will collect the name for each map and put it into an ArrayList.
     *
     * @return an ArrayList containing all the map names
     */
    private ArrayList<String> collectMapNames(){
        ArrayList list = new ArrayList<>();
        for (int i = 0; i < mapList.getLength(); i++) {
            Node node = mapList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                list.add(getTagValue("name", element));
            }
        }
        return list;
    }

    /**
     * Will generate the specified map from the file storing the maps.
     *
     * @param mapName the name of the desired map
     * @return the map asked for, if it doesn't exist null will be returned
     */
    public Map loadMap(String mapName) {
        Map map = null;
        for (int i = 0; i < mapList.getLength(); i++) {
            Node node = mapList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if (mapName.compareTo(getTagValue("name", element)) == 0) {
                    map = makeMap(element);
                }
            }
        }
        return map;
    }

    /**
     * Will construct a map from the information collected from the map-file.
     *
     * @param e an Element containing all the elements of the map.
     * @return the finished Map.
     */
    private Map makeMap(Element e) {
        Map map = new Map();
        NodeList mapInfo = e.getChildNodes();

        /*Set the simple values*/
        map.setName(getTagValue("name", e));
        map.setWaves(Integer.parseInt(getTagValue("waves", e)));
        map.setWinScore(Integer.parseInt(getTagValue("winScore", e)));
        map.setStartingGold(Integer.parseInt(getTagValue("startingGold", e)));

        /*Find all the tiles of the map and put them into the Map object*/
        for (int i = 0; i < mapInfo.getLength(); i++) {
            Node node = mapInfo.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String nodeName = node.getNodeName();
                if (nodeName.compareTo("tile") == 0) {
                    try {
                        Tile t = constructTile(node);
                        map.addTile(t);
                    } catch (WrongClassTypeException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

        return map;
    }

    /**
     * Will return the value held by a tag in the xml document.
     *
     * @param tag     the name of the tag
     * @param element the element holding the tag
     * @return a string representing the value
     */
    private String getTagValue(String tag, Element element) {
        NodeList nlList =
                element.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = nlList.item(0);
        return nValue.getNodeValue();
    }

    /**
     * Will make a Tile object to put into the map.
     *
     * @param node a Node containing the tiles information
     * @return a finished tile.
     * @throws WrongClassTypeException when the class specified to be used as a
     * tile doesn't extend the Tile class
     */
    private Tile constructTile(Node node) throws WrongClassTypeException {
        Node tileTypeNode = node.getAttributes().getNamedItem("tileType");

        /*Gets the name of the tile that should be loaded*/
        String type = "modell.tile." + tileTypeNode.getNodeValue();
        Class<?> tileType = tileTypes.get(type);

        if (tileType == null) {
            tileType = readClass(type);
            tileTypes.put(type, tileType);
        }

        /*Get the position of the tile*/
        NodeList positionList = ((Element)node).getElementsByTagName("sendToPos");

        Node tilePos = ((Element)node).getElementsByTagName("tilePos").item(0);
        Position p = extractPosition(tilePos);

        Tile tile = null;

        try {
            Constructor constructor;
            constructor = tileType.getConstructor(Position.class);

            tile = (Tile) constructor.newInstance(p);

            /* If the tile is a PathTile:
             * Add position(s) to which the tile will send units.
             */
            if (PathTile.class.isAssignableFrom(tile.getClass())) {
                PathTile pTile = (PathTile) tile;
                for (int i = 0; i < positionList.getLength(); i++) {
                    pTile.sendToPos(extractPosition(positionList.item(i)));
                }
            }
        } catch (InstantiationException e) {
            //TODO exception handling
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return tile;
    }

    /**
     * Will extract the position from a position element in the .xml file
     *
     * @param pos the node containing the information
     * @return an instance of Position.
     */
    private Position extractPosition(Node pos) {
        int x = Integer.parseInt(pos.getAttributes().getNamedItem("column").getNodeValue())-1;
        int y = Integer.parseInt(pos.getAttributes().getNamedItem("row").getNodeValue())-1;
        return new Position(x, y);
    }

    /**
     * Loads a tile class and returns the class.
     *
     * @param className the name of the class.
     * @return the Class with the specified name.
     * @throws WrongClassTypeException when the class doesn't extend Tile
     */
    private Class<?> readClass(String className) throws WrongClassTypeException {
        Class c = null;

        try {
            /*start an class loader that use URLs*/
            URL[] classUrls = {};
            URLClassLoader ucl = new URLClassLoader(classUrls);

            c = ucl.loadClass(className);
        } catch (ClassNotFoundException e) {
            //TODO exception handling
            e.printStackTrace();
        }

        /*Check if the class loaded actually is a subclass of Tile*/
        if (!Tile.class.isAssignableFrom(c)) {
            throw new WrongClassTypeException("The specified tile doesn't " +
                    "extend the Tile class");
        }

        tileTypes.put(c.getName(), c);
        return c;
    }

    /**
     * Checks if the map given is valid according to the schema specified.
     *
     * @param map the path to the map.
     * @return true if the map is valid, else false.
     */
    private boolean validateMap(StreamSource map) throws IOException, SAXException {
        String schemaPath = "mapTemplate.xsd";

        String schemaLang = "http://www.w3.org/2001/XMLSchema";
        SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
        URL url = getClass().getResource(schemaPath);
        Schema schema = factory.newSchema(new StreamSource(url.openStream()));
        Validator validator = schema.newValidator();
        validator.validate(map);
        return true;
    }

    /**
     * Returns an ArrayList with all the names of the map in the specified file.
     *
     * @return a list of all map names
     */
    public ArrayList<String> getMapNames() {
        return mapNames;
    }
}
