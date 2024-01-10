import { useEffect, useState } from "react"

const Sidebar = () => {
  const [currentLink, setCurrentLink] = useState('');
  const [nextIndex, setNextIndex] = useState(-1);
  const [previousIndex, setPrevoiusIndex] = useState(-1);

  useEffect(() => {
    const currentPath = window.location.pathname;
    setCurrentLink(currentPath);

    const activeIndex = links.find((link) => link.to === currentPath).id;

    if(activeIndex - 1 > 0){
      setPrevoiusIndex(activeIndex - 1);
    }
    
    setNextIndex(activeIndex+1);
  }, [])
  
  const links = [
    {
      id: 1,
      to: "/",
      icon: "src/assets/images/landing/logo.png"
    },
    {
      id: 2,
      to: "/home",
      icon: "src/assets/images/misc/navbar-games.png"
    },
    {
      id: 3,
      to: "/reviews",
      icon: "src/assets/images/misc/navbar-reviews.png"
    },
    {
      id: 4,
      to: "/news",
      icon: "src/assets/images/misc/navbar-news.png"
    },
    {
      to: "/gamelist",
    },
  ]

  return (
    <nav className="w-[6.25rem] h-screen sticky top-0 bg-[#1d1d1f] min-w-[6.25rem]">
      <ul className="navbar-nav">
        {links.slice(0, 4).map((link) =>
          <li 
            key={link.id}
            className={`nav-item 
            ${currentLink === link.to ? "selected-nav" : ""} 
            ${link.to === '/' ? "home-nav-logo" : ""} 
            ${link.id === previousIndex ? 'selected-nav-prev' : ""}
            ${link.id === nextIndex ? 'selected-nav-next' : ""}`}>
            <a href={link.to}>
              <img 
              className={`navbar-icon ${currentLink !== link.to ? "hover:scale-125" : ""} `} 
              src={link.icon} 
              alt="NavIcon" />
            </a>
          </li>
        )}
        <div className={`bg-[#ff5c00] w-full h-full ${nextIndex === 5 ? 'selected-nav-next' : ""}`}></div>
      </ul>
    </nav>
  )
}

export default Sidebar
