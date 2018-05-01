<?php
/**
 * A single event in a events loop. Used by eo-loop-single-event.php
 *
 ***************** NOTICE: *****************
 *  Do not make changes to this file. Any changes made to this file
 * will be overwritten if the plug-in is updated.
 *
 * To overwrite this template with your own, make a copy of it (with the same name)
 * in your theme directory.
 *
 * WordPress will automatically prioritise the template in your theme directory.
 ***************** NOTICE: *****************
 *
 * @package Event Organiser (plug-in)
 * @since 3.0.0
 */
?>

<article id="post-<?php the_ID(); ?>" class="news-item">
	<div class="news-item-top">
		<div class="eo-event-details event-entry-meta">
				
			<?php
			echo eo_get_event_meta_list();
			?>
				
		</div><!-- .event-entry-meta -->

		<small class="eo-event-date"> 
			<?php
				//Formats the start & end date of the event
				echo eo_format_event_occurrence();
			?>
			<?php if( get_field('event_city') ): ?>
			 - <?php the_field('event_city'); ?>
			<?php endif; ?>
		</small>
	</div>

	<h4 class="eo-event-title entry-title">
		<a href="<?php the_permalink(); ?>" itemprop="url">
			<span itemprop="summary"><?php the_title() ?></span>
		</a>
	</h4>
	
	<!-- <div class="eo-event-content" itemprop="description"><?php the_excerpt(); ?></div> -->

</article>
