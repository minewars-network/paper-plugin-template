package pl.minewars.example.plugin.loader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.jetbrains.annotations.NotNull;

import java.io.InputStreamReader;
import java.io.Reader;

import static java.util.Objects.requireNonNull;
import static org.eclipse.aether.repository.RemoteRepository.Builder;

@SuppressWarnings("UnstableApiUsage")
public class PluginDependencyLoader implements PluginLoader {

    @Override
    public void classloader(
            @NotNull PluginClasspathBuilder classpathBuilder
    ) {

        MavenLibraryResolver mavenLibraryResolver = new MavenLibraryResolver();

        this.paperLibraries.get("dependencies").getAsJsonArray()
                .asList().stream()
                .map((dependency) -> new Dependency(new DefaultArtifact(dependency.getAsString()), null))
                .forEach(mavenLibraryResolver::addDependency);

        this.paperLibraries.getAsJsonObject("repositories")
                .entrySet().stream()
                .map(
                        (entry) -> new Builder(entry.getKey(), "default", entry.getValue().getAsString())
                                .build()
                ).forEach(mavenLibraryResolver::addRepository);

        classpathBuilder.addLibrary(mavenLibraryResolver);

    }

    protected final Gson gson = new Gson();
    protected final JsonObject paperLibraries; {

        try (
                Reader reader = new InputStreamReader(requireNonNull(getClass().getResourceAsStream("/paper-libraries.json")))
        ) {
            this.paperLibraries = this.gson.fromJson(reader, JsonObject.class);
        } catch (@NotNull Exception exception) {
            throw new RuntimeException("Unable to read 'paper-libraries.json' file.", exception);
        }

    }

}
