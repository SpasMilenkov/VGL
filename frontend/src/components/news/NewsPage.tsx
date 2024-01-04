import NewsArticleCard from "./NewsArticleCard";
const NewsPage = () => {
  const data = [
    {
      id: "5410576585173478423",
      title: "Is Warframe Steam Deck compatible?",
      url: "https://steamstore-a.akamaihd.net/news/externalpost/PCGamesN/5410576585173478423",
      author: "editor@pcgamesn.com",
      contents: `<strong>Is Warframe Steam Deck compatible?</strong> Any developer who can proudly boast more than a decade of activity with a live-service title knows how to keep up with the market. <strong>Warframe</strong> is a game that doesn't necessarily scream 'play me on a handheld', yet the current trend of optimizing games for devices like...`,
      feedLabel: "PCGamesN",
      date: 1704195673,
      feedName: "PCGamesN",
      appid: 230410,
    },
    {
      id: "5410576585158897126",
      title: "Warframe system requirements",
      url: "https://steamstore-a.akamaihd.net/news/externalpost/PCGamesN/5410576585158897126",
      author: "editor@pcgamesn.com",
      contents:
        "<strong>What are the Warframe system requirements?</strong> There can be no doubting the quality of Warframe's community given that the game has been live for over a decade. A big part of this is also the approachable system requirements that don't scare off new players. You won't need one of the <a href=\"https://www.pcgamesn.com/best-graphics-card\">best graphics cards...</a>",
      feedLabel: "PCGamesN",
      date: 1703802483,
      feedName: "PCGamesN",
      appid: 230410,
    },
    {
      id: "5410576585134267814",
      title: "Cumulus Collection Giveaway",
      url: "https://steamstore-a.akamaihd.net/news/externalpost/steam_community_announcements/5410576585134267814",
      author: "Intrepid FishToot!",
      contents:
        "https://store.steampowered.com/app/2716340/Warframe_Cumulus_Collection/ Add the Cumulus Collection to your Arsenal for free! These fashionable items will be available to claim through Steam from December 15, 2023 to January 10, 2024. The Cumulus Collection includes: Cirrus Armor Bundle Cumulus Syand...",
      feedLabel: "Community Announcements",
      date: 1703266342,
      feedName: "steam_community_announcements",
      appid: 230410,
    },
    {
      id: "5410576585130936195",
      title: "Prime Resurgence Event",
      url: "https://steamstore-a.akamaihd.net/news/externalpost/steam_community_announcements/5410576585130936195",
      author: "UnknownSamee",
      contents:
        "Over the next few weeks, several rotations for select Prime Warframes and their signature Accessories will be up for grabs courtesy of Varzia Dax in Maroo’s Bazaar. It all starts with our first round of offerings from now until January 4, 2024, at 2 p.m. ET. Current Rotation: Mesa PrimeHydroid Prime...",
      feedLabel: "Community Announcements",
      date: 1703187405,
      feedName: "steam_community_announcements",
      appid: 230410,
    },
    {
      id: "5409450219988085871",
      title: "Operation: Gargoyle’s Cry",
      url: "https://steamstore-a.akamaihd.net/news/externalpost/steam_community_announcements/5409450219988085871",
      author: "LukeSimms",
      contents:
        "SPOILERS: This Operation is meant for players who have already completed the “Whispers in the Walls” Quest! {STEAM_CLAN_IMAGE}/4437469/d5e8e24cdb546f264c529ca6414fa0cfa40eeb4b.png You may have fended off The Man in the Wall’s opening gambit and faced the Murmur beneath Deimos, but the worst is yet t...",
      feedLabel: "Community Announcements",
      date: 1702926963,
      feedName: "steam_community_announcements",
      appid: 230410,
    },
    {
      id: "5409450219977258198",
      title: "Prime Resurgence Event coming soon",
      url: "https://steamstore-a.akamaihd.net/news/externalpost/steam_community_announcements/5409450219977258198",
      author: "Markus",
      contents:
        "Eager for another chance at an ever-elusive Prime Warframe? The Prime Resurgence Event has got you covered! {STEAM_CLAN_IMAGE}/4437469/2e77c70bad9504029d7091bb0b6787f2d304957c.png Starting on December 21, a series of rotations for select Prime Warframes and their signature Accessories will be availa...",
      feedLabel: "Community Announcements",
      date: 1702670255,
      feedName: "steam_community_announcements",
      appid: 230410,
    },
    {
      id: "7585814657461158557",
      title: "Whispers in the Walls Alerts",
      url: "https://steamstore-a.akamaihd.net/news/externalpost/steam_community_announcements/7585814657461158557",
      author: "LukeSimms",
      contents:
        "Need some extra stopping power before you venture into Albrecht Entrati’s labs, Tenno? Add Sevagoth’s sleek, signature Epitaph Secondary Weapon and the frighteningly powerful Warframe himself to your Arsenal with a batch of Whispers in the Walls Alerts! {STEAM_CLAN_IMAGE}/4437469/23df835c7a4856afb19...",
      feedLabel: "Community Announcements",
      date: 1702575008,
      feedName: "steam_community_announcements",
      appid: 230410,
    },
    {
      id: "5397065151386590734",
      title: "Available Now: Whispers in the Walls",
      url: "https://steamstore-a.akamaihd.net/news/externalpost/steam_community_announcements/5397065151386590734",
      author: "LukeSimms",
      contents:
        "The next chapter in Warframe’s cinematic narrative has arrived! The whispers grow louder, Tenno. Delve into Albrecht’s labs and discover the deadly secrets hidden within. Release the radioactive power of Qorvex, the 55th Warframe, from concrete containment and master his Abilities. Wield the new Tom...",
      feedLabel: "Community Announcements",
      date: 1702487395,
      feedName: "steam_community_announcements",
      appid: 230410,
    },
    {
      id: "5397065151386573292",
      title: "Initiate Power Pack",
      url: "https://steamstore-a.akamaihd.net/news/externalpost/steam_community_announcements/5397065151386573292",
      author: "LukeSimms",
      contents:
        "Propel yourself through the Star Chart and Main Story Quest path with the Initiate Power Pack! This trove of fully upgraded mainstay Mods and unique Warframe Augments has been handpicked to aid new Warframe players on their journey to Whispers in the Walls and beyond — allowing you to join your frie...",
      feedLabel: "Community Announcements",
      date: 1702486979,
      feedName: "steam_community_announcements",
      appid: 230410,
    },
    {
      id: "5397065151386487026",
      title: "Sanctum Supporter Pack",
      url: "https://steamstore-a.akamaihd.net/news/externalpost/steam_community_announcements/5397065151386487026",
      author: "LukeSimms",
      contents:
        "Wield Albrecht Entrati's most lavish instruments with the Sanctum Supporter Pack! Drown out any unpleasant whispers with a collection of Arsenal-amplifying curiosities and opulent Decorations that are sure to speak volumes. {STEAM_CLAN_IMAGE}/4437469/5f9223bb2c104ab98877b634bf506cfddc7042a2.png Be t...",
      feedLabel: "Community Announcements",
      date: 1702484939,
      feedName: "steam_community_announcements",
      appid: 230410,
    },
  ];
  return (
    <div>
      <main id="main-content">
        <section id="latest-section">
          <div className="section-title">Latest</div>
          {data.map((card, index) => (
            <NewsArticleCard
              key={index}
              newsAuthor={card.author}
              newsTitle={card.title}
              newsDesc={card.contents}
              newsTime={card.date.toString()}
              imgUrl={`https://cdn.cloudflare.steamstatic.com/steam/apps/${card.appid}/header.jpg`}
            ></NewsArticleCard>
          ))}
        </section>
      </main>
    </div>
  );
};

export default NewsPage;
