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
('BestTV 2', 'http://pastebin.com/raw.php?i=VYa2iGuW', 0, true),
('BestTV World', 'http://pastebin.com/raw.php?i=0v7dwrnb', 0, true)
;
