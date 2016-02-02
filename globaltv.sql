CREATE DATABASE if not exists globaltv
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_unicode_ci;

USE globaltv;
INSERT INTO `PLAYLIST`
(name, url, type, enable)
VALUES
('Torrent TV (super-pomoyka)', 'http://super-pomoyka.us.to/trash/ttv-list/ttv.m3u', 1, true),
('Torrent TV (super-pomoyka)2', 'http://super-pomoyka.us.to/trash/ttv-list/ttv.all.player.m3u', 1, false),
('Torrent TV (allfon.tv)', 'http://allfon.tv/autogenplaylist/allfontv.m3u', 0, true),
('BestTV', 'http://pastebin.com/raw.php?i=p9f2GF2C', 0, true),
('BestTV SlyNet', 'http://iptv.slynet.tv/FreeSlyNet.m3u', 0, true),
('BestTV World', 'http://pastebin.com/raw.php?i=0v7dwrnb', 0, true),
('Adults SlyNet', 'http://slynet.do.am/AdultsSlyNet.m3u', 0, false),
('Webcam World', 'http://pastebin.com/raw.php?i=4zkncmD3', 0, false),
('Peers tv SlyNet', 'http://slynet.do.am/PeerstvSlyNet.m3u', 0, true),
('Kinodrom SlyNet', 'http://slynet.do.am/KinodromSlyNet.m3u', 0, true),
('TV.posle.info', 'http://tv.posle.info/smart_tv.m3u', 0, true),
('tv4p.ru Zabava TV', 'http://tv4p.ru/01z.m3u', 0, false),
('tv4p.ru Vintera TV', 'http://tv4p.ru/05v.m3u', 0, true),
('tv4p.ru Bonus TV', 'http://tv4p.ru/04b.m3u', 0, true),
('Denms.ru', 'http://iptv.denms.ru', 0, true),
('Moyo.tv', 'http://moa.ucoz.com/XML/moyo.m3u', 0, true),
('Lamp.ufa-it.ru', 'http://iptv.lamp.ufa-it.ru/generate_m3u', 0, true),
('Playlist-iptv @ Ucoz', 'http://playlist-iptv.ucoz.ua/trenie/m3u/new_playlist.m3u', 0, true),
('Lanet', 'http://lanet.tv/playlist.m3u', 0, true),
('Rubin+', 'http://rubinplus.net/unicast.m3u', 0, true),
('Iptv-360', 'http://iptv-360.esy.es/public_http/pleulist/stream.m3u', 0, false),
('ListIPTV', 'http://listiptv.ru/iptv.m3u', 0, true),
('dostupnoe-iptv', 'http://iptv.rumedia.biz/dostupnoe-iptv.m3u', 0, true),
('edem.tv', 'https://edem.tv/playlists/uplist/c0501458db7536739c23049fda773bf6/edem_pl.m3u8', 0, true),
('RealWeb', 'http://iptv.usafreespace.com/webtv.m3u', 0, true)
;
