package com.easyapps.singerpro.query.model.lyric;

import com.easyapps.singerpro.infrastructure.persistence.lyric.FileSystemException;

import java.util.List;

/**
 * Finder for Lyrics objects.
 * Created by daniel on 04/10/2016.
 */

public interface ILyricFinder {
    List<LyricQueryModel> getAll();

    List<LyricQueryModel> getFromSetList(String setListName) throws FileSystemException;

    List<String> getAllLyricNamesFromPlaylist(String playlistName, String currentLyricName) throws FileSystemException;
}
