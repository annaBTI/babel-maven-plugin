package com.jarslab.maven.babel.plugin;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

class TargetFileWriter
{
    private final String sourceDir;
    private final String targetDir;
    private final String prefix;

    TargetFileWriter(final String sourceDir,
                     final String targetDir,
                     final String prefix)
    {
        this.sourceDir = requireNonNull(sourceDir);
        this.targetDir = requireNonNull(targetDir);
        this.prefix = requireNonNull(prefix);
    }

    void writeTargetFile(final Path sourceFile,
                         final byte[] content)
    {
        final Path sourceFileName = sourceFile.getFileName();
        final String targetDirectory = sourceFile.toString()
                .replace(sourceFileName.toString(), "")
                .replace(sourceDir, targetDir);
        try {
            Files.createDirectories(Paths.get(targetDirectory));
            Files.write(Paths.get(targetDirectory, prefix + sourceFileName), content);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
