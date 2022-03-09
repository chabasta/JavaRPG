# JavaRPG
<p>Simple rpg game written in java programming language.</p>
<p>Hlavním cílem hry je zabít všechny duchy, abyste získal klíč a otevřel dveře.</p>

<h1>Manuál pro uživatele:</h1>

<h3>Pohyb pomocí tlačítek:<h3>
  <ul>
    <li><strong>w</strong> - jít nahoru</li>
    <li><strong>a</strong> - jít vlevo</li>
    <li><strong>s</strong> - jít dolů</li>
    <li><strong>d</strong> - jít vpravo</li>
  </ul>

<h3>Interakce s objekty nebo vyučující pomocí:</h3>
    <ul>
      <li>e - použít</li>
    </ul>
  
  
<h1>Technický manuál:</h2>
<h3>Struktura aplikace:</h3>
<h4>Model:<h4>
 <ul>
   <li>Student - Třída representuje postavu za kterou hraje user, neboli nps které chodí po mapě.
   <li>Item - Třída representuje objekty nacházející na mapě nebo v inventářů.
   <li>Employer - Třída representuje postavu pracovníků na univerzitě.
 </ul>
<h4>Controller:/<h4>
 <ul>
   <li>MainController - Třída zajišťuje vztah mezi třídami View a Model.
 </ul>
<h4>View:</h4>
 <ul>
    <li>Display - Třída representuje okénko ve kterém běží hra a zajišťuje jeho update.
    <li>Map - Třída representuje mapu s objekty a lokacemi.
    <li>Sound - Třída representuje metody pro práce se zvuky ve hře.
 </ul>

Pro implementace hry budlo požite knihovny: swing, awt
