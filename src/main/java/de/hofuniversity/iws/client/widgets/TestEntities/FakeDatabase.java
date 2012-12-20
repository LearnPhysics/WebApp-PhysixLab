/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.TestEntities;

import java.util.*;

import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.playn.PlayNWidget;
import de.hofuniversity.iws.client.playn.games.KineticWars;

/**
 *
 * @author Oliver
 */
public class FakeDatabase {

    private static FakeDatabase eh = null;
    private List<TestThema> themen;

    private FakeDatabase() {
        themen = new LinkedList<TestThema>();
        buildTestData();
    }

    public static FakeDatabase getInstance() {
        if (eh == null) {
            eh = new FakeDatabase();
        }
        return eh;
    }

    public List<TestThema> getAllThemes() {
        return themen;
    }

    private void buildTestData() {
        TestThema kinetik = new TestThema();
        TestThema elekt = new TestThema();
        TestThema thermo = new TestThema();

        kinetik.setTitle("Kinetik");
        kinetik.setDescription("Die Kinetik (gr. kinesis Bewegung) ist ein Teilgebiet der Mechanik und beschreibt die Änderung der Bewegungsgrößen (Weg, Zeit, Geschwindigkeit und Beschleunigung) unter Einwirkung von Kräften im Raum. Die Kinetik steht im Gegensatz zur Statik, die sich mit dem Kräftegleichgewicht an nicht beschleunigten Körpern beschäftigt. Beide zusammen bilden die Dynamik, die sich mit der Wirkung von Kräften befasst.");
        kinetik.setImageURL("images/UserHome/Thema_Kinetik.png");
        elekt.setTitle("Elektrizität");
        elekt.setDescription("Elektrizität (von griechisch elektron „Bernstein“) ist der physikalische Oberbegriff für alle Phänomene, die ihre Ursache in ruhender oder bewegter elektrischer Ladung haben. Dies umfasst viele aus dem Alltag bekannte Phänomene wie Blitze oder die Kraftwirkung des Magnetismus. Der Begriff Elektrizität ist in der Naturwissenschaft nicht streng abgegrenzt, es werden aber bestimmte Eigenschaften zum Kernbereich der Elektrizität gezählt.");
        elekt.setImageURL("images/UserHome/Thema_Elekt.png");
        thermo.setTitle("Thermodynamik");
        thermo.setDescription("Die Thermodynamik (von altgriechisch thermós „warm“ sowie dynamis „Kraft“),[1] auch als Wärmelehre bezeichnet, ist ein Teilgebiet der klassischen Physik. Sie beschäftigt sich mit der Möglichkeit, durch Umverteilen von Energie zwischen ihren verschiedenen Erscheinungsformen Arbeit zu verrichten. Die Grundlagen der Thermodynamik wurden aus dem Studium der Volumen-, Druck-, Temperaturverhältnisse bei Dampfmaschinen entwickelt.");
        thermo.setImageURL("images/UserHome/Thema_Thermo.png");

        TestGame game = new TestGame();
        game.setTitle("Kinetic Wars");
        game.setDescription("Bring die Bauten deiner Gegner zum Einsturz.");
        game.setImageURL("images/Thema/gameTest01.jpg");
        Widget w = new PlayNWidget(new KineticWars());
        //normally with css
        w.setWidth(600 + "px");
        game.setGameWidget(w);
        kinetik.getGames().add(game);

        TestGame game0 = new TestGame();
        game0.setTitle("Go Fishing");
        game0.setDescription("Es handelt sich hier um ein reines Testspiel.");
        game0.setImageURL("images/Thema/gameTest02.jpg");
        game0.setGameWidget(new Image("images/ReplacementImages/gameWidget02.png"));
        kinetik.getGames().add(game0);

        TestLektion parent = new TestLektion();
        parent.setTitle("Freier Fall");
        parent.setPreviewURL("images/ReplacementImages/FreeFallPreview.png");
        parent.setParent(null);

        TestLektion pendel = new TestLektion();
        pendel.setTitle("Das Fadenpendel");
        pendel.setPreviewURL("images/ReplacementImages/LektionPendelPreview.jpg");
        pendel.setLessonText("Die Schwingungsperiode eines Fadenpendels hängt nur von der Länge des Pendels ab, nicht jedoch von der Masse des Pendels, wie man zunächst vermuten könnte. Um zu verstehen, weshalb dies so ist und um die Schwingungsperionde berechnen zu können, muss man die Bewegungsgleichung für ein Fadenpendel aufstellen und lösen. Die Erde zieht die Masse des Pendels mit der Gewichts-Kraft G senkrecht nach unten. Diese Kraft, das Gewicht des Pendels, ist umso grösser, je grösser die Masse des Pendels ist. Eine weitere Kraft, die an der Pendelmasse angreift, wird von der Pendelschnur ausgeübt. Sie zeigt immer in Richtung Pendelaufhängung und bewirkt, dass die Pendelmasse auf einem Kreisbogen gehalten wird. Die Grösse dieser Kraft ist für die Berechnung der Pendelbewegung nicht von Bedeutung, wie wir weiter unten noch sehen werden.Die Kraft G kann geometrisch in die zwei Komponenten Fr (r=radial) und Ft (t=tangential) zerlegt werden.Solange das Pendel nicht zu weit ausgelenkt wird, wirkt die Komponente Fr immer in Gegenrichtung des Fadens und bewirkt, dass der Faden gespannt bleibt. Die Kraft vom Faden in Richtung Aufhängung ist gleich der Kraft Fr plus der Zentripetalkraft und zeigt immer in die Gegenrichtung von Fr. Die Zentripetalkraft entsteht durch die kreisförmige Bewegung der Pendelmasse. Es ist jene Kraft, die man spüren würde, wenn man das Pendel im Kreis herumschwingen würde. Sie ist umso grösser, je schneller die Pendelmasse schwingt und je grösser die Masse des Pendels ist. Die Zentripetalkraft sorgt dafür, dass die Pendelmasse nicht einfach geradlinig davon fliegt, sondern auf einer Kreisbahn gehalten wird. Auf das Hin- und Herschwingen des Pendels haben all diese radialen Komponenten aber keinen Einfluss, weil sie immer senkrecht zur Bewegungsrichtung der Masse wirken und die Pendelmasse kann sich entlang des starren Fadens ja nicht frei bewegen. Diese Kräfte müssen wir deshalb nicht berechnen. Anders wäre es, wenn es sich um einen dehnbaren Gummifaden handeln würde. Die Komponente Ft wirkt immer tangential und bewirkt, dass die Pendelmasse in dieser Richtung beschleunigt oder abgebremst wird. Diese Kraft ist es, welche das Pendel schwingen lässt.");
        pendel.setExperiment(new Image("images/ReplacementImages/LektionPendelWidget.jpg"));
        pendel.setFormular(new Image("images/ReplacementImages/PendelFormelWidget.gif"));
        pendel.setParent(parent);

        TestTest testPendel = new TestTest();
        testPendel.setTitle("Höchstgeschwindigkeit");
        testPendel.setDescription("Wie man eine Differezialgleichung 2. Ordnung löst ist nicht einfach. Wir müssen eine Funktion f(φ(t)) finden, welche links und rechts von (VIII) eingesetzt werden kann, sodass beide Seiten das Selbe ergeben. Auf der linken Seite muss dabei f(φ(t)) zweimal abgeleitet werden. Um eine solche Funktion f zu finden, schaut man in einem Formelbuch nach, ob man vielleicht schon eine Lösung gefunden hat. Oder man beobachtet, wie sich das Objekt bewegt und versucht daraus eine Formel abzuleiten und überprüft dann, ob die so gefundene Formel korrekt ist. Oder man sucht ähnliche physikalische Systeme bzw. schaut, ob das vorliegende System durch ein ähnliches System angenähert werden kann, für welches man bereits eine Lösung gefunden hat. Im Falle des Fadenpendels können wir beobachten, dass das Pendel periodisch hin und her schwingt. f könnte also eine Sinus-Funktion sein. Wenn man diesen Ansatz überprüft, indem man f = a · sin(b·φ(t) + c) einsetzt, stellt man jedoch fest, dass der Ansatz nicht korrekt ist. Schauen wir also, ob es ein ähnliches System gibt, für das wir die Lösung kennen, und das annähernd wie ein Fadenpendel reagiert. Wir vermuten mal, dass ein Federpendel einem Fadenpendel ähnlich sein könnte und schauen uns die Bewegungsgleichung des Federpendels an, für welches wir die Lösung nachschauen können:");
        testPendel.setSolution(20);
        testPendel.setIllustration(new Image("images/ReplacementImages/PendelTestIllustration.JPG"));
        testPendel.setPassed(false);

        pendel.setTest(testPendel);

        kinetik.getLektionen().add(parent);
        kinetik.getLektionen().add(pendel);

        themen.add(kinetik);
        themen.add(elekt);
        themen.add(thermo);
    }
}
