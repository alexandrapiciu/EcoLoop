package com.ecoloop.service;

import com.ecoloop.model.Category;
import com.ecoloop.model.Tool;
import com.ecoloop.model.ToolStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Holds mock tool data in memory. No DB behind this - the list is seeded on startup.
@Service
public class ToolService {

    private final List<Tool> tools = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // Seed the in-memory list with example tools so the app has content to show.
    @PostConstruct
    public void init() {
        add(new Tool(null,
                "Professional Power Drill",
                "Cordless 18V power drill, two batteries included. Great for home renovation and DIY projects.",
                "Mihai Popescu", "Bucharest - Sector 3",
                12.0,
                "https://images.unsplash.com/photo-1504148455328-c376907d081c?w=600",
                Category.POWER_TOOLS,
                "Available weekdays and weekends",
                4.8, "Worked perfectly, battery lasted all day.",
                ToolStatus.AVAILABLE));

        add(new Tool(null,
                "Gas-Powered Lawn Mower",
                "Reliable gas mower suitable for medium to large yards. Freshly serviced.",
                "Ana Ionescu", "Cluj-Napoca - Grigorescu",
                25.0,
                "https://images.unsplash.com/photo-1589923188900-85dae523342b?w=600",
                Category.GARDEN,
                "Available on weekends",
                4.5, "Powerful and easy to start.",
                ToolStatus.AVAILABLE));

        add(new Tool(null,
                "Extension Ladder (6m)",
                "Aluminum extension ladder reaching up to 6 meters. Stable and lightweight.",
                "Vlad Dumitrescu", "Timisoara - Central",
                10.0,
                "https://images.unsplash.com/photo-1581578017420-4b7b05ab0094?w=600",
                Category.LADDERS,
                "Available Mon-Fri",
                4.7, "Perfect for painting the facade.",
                ToolStatus.AVAILABLE));

        add(new Tool(null,
                "Electric Hedge Trimmer",
                "Lightweight electric hedge trimmer with 50cm blade. Ideal for trimming small to medium hedges.",
                "Elena Marinescu", "Bucharest - Sector 6",
                8.0,
                "https://images.unsplash.com/photo-1416879595882-3373a0480b5b?w=600",
                Category.GARDEN,
                "Available any day",
                4.6, "Cut through everything smoothly.",
                ToolStatus.AVAILABLE));

        add(new Tool(null,
                "High-Pressure Washer",
                "2000W pressure washer. Comes with multiple nozzles for cars, patios and facades.",
                "Andrei Stoica", "Iasi - Centru",
                18.0,
                "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=600",
                Category.CLEANING,
                "Available next week",
                4.9, "Made my driveway look new!",
                ToolStatus.AVAILABLE));

        add(new Tool(null,
                "Complete Hand Toolbox",
                "Full hand-tool kit: screwdrivers, hammer, wrenches, pliers and a tape measure.",
                "Cristina Neagu", "Brasov - Astra",
                6.0,
                "https://images.unsplash.com/photo-1530124566582-a618bc2615dc?w=600",
                Category.HAND_TOOLS,
                "Always available",
                4.4, "Had everything I needed.",
                ToolStatus.AVAILABLE));

        add(new Tool(null,
                "Circular Saw",
                "Corded 1400W circular saw. Good for planks up to 65mm thick.",
                "Radu Enache", "Bucharest - Sector 1",
                15.0,
                "https://images.unsplash.com/photo-1572981779307-38b8cabb2407?w=600",
                Category.POWER_TOOLS,
                "Available weekends only",
                4.3, "Clean cuts, a bit heavy.",
                ToolStatus.AVAILABLE));

        add(new Tool(null,
                "Garden Tiller",
                "Compact electric tiller for preparing your vegetable garden or flower beds.",
                "Ioana Pop", "Sibiu - Turnisor",
                20.0,
                "https://images.unsplash.com/photo-1585513553738-84abdc9c7b02?w=600",
                Category.GARDEN,
                "Available in April",
                4.7, "Saved me a whole afternoon of digging.",
                ToolStatus.AVAILABLE));
    }

    public List<Tool> findAll() {
        return tools;
    }

    public Optional<Tool> findById(Long id) {
        return tools.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    // Simple search by name or description (case-insensitive) and optional category.
    public List<Tool> search(String query, String category) {
        String q = query == null ? "" : query.trim().toLowerCase();
        return tools.stream()
                .filter(t -> q.isEmpty()
                        || t.getName().toLowerCase().contains(q)
                        || t.getDescription().toLowerCase().contains(q))
                .filter(t -> category == null || category.isBlank()
                        || t.getCategory().name().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Adds a tool to the in-memory list. Assigns an id automatically.
    public Tool add(Tool tool) {
        if (tool.getId() == null) {
            tool.setId(idGenerator.getAndIncrement());
        } else {
            idGenerator.set(Math.max(idGenerator.get(), tool.getId() + 1));
        }
        if (tool.getStatus() == null) {
            tool.setStatus(ToolStatus.AVAILABLE);
        }
        if (tool.getImageUrl() == null || tool.getImageUrl().isBlank()) {
            tool.setImageUrl("https://via.placeholder.com/600x400?text=EcoLoop+Tool");
        }
        tools.add(tool);
        return tool;
    }
}
